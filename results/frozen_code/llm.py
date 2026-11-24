"""Ollama chat client with token/time accounting."""
import json, time, requests


class LLM:
    def __init__(self, host, model, temperature=0.2, max_tokens=4096, num_ctx=20480, seed=0):
        self.url = f"http://{host}/api/chat"
        self.model, self.temperature, self.max_tokens, self.num_ctx, self.seed = \
            model, temperature, max_tokens, num_ctx, seed
        self.calls = self.tokens_in = self.tokens_out = 0
        self.time = 0.0
        self.log = []

    def snapshot(self):
        return {"llm_calls": self.calls, "tokens_in": self.tokens_in, "tokens_out": self.tokens_out,
                "wall_time_llm_s": self.time}

    def chat(self, system, user, tag):
        body = {"model": self.model, "stream": False, "keep_alive": -1,
                "messages": [{"role": "system", "content": system}, {"role": "user", "content": user}],
                "options": {"temperature": self.temperature, "num_predict": self.max_tokens,
                            "num_ctx": self.num_ctx, "seed": self.seed}}
        t = time.time()
        last = None
        for attempt in range(4):
            try:
                r = requests.post(self.url, json=body, timeout=1800)
                r.raise_for_status()
                d = r.json()
                break
            except Exception as e:  # retry transient server errors
                last = e
                time.sleep(10 * (attempt + 1))
        else:
            self.time += time.time() - t
            raise RuntimeError(f"LLM call failed after retries: {last}")
        dt = time.time() - t
        self.calls += 1
        self.time += dt
        tin, tout = d.get("prompt_eval_count", 0), d.get("eval_count", 0)
        self.tokens_in += tin
        self.tokens_out += tout
        content = d["message"]["content"]
        rec = {"tag": tag, "tokens_in": tin, "tokens_out": tout, "seconds": dt,
               "done_reason": d.get("done_reason")}
        self.log.append(rec)
        return content, rec
