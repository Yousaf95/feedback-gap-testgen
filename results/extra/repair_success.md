# Repair success (R configurations)

A run *needs repair* if its R folder contains at least one repair round; it is *fixed by repair* if the compile log after the last repair round contains no error in the generated test file (no pruning needed).

| Model | runs needing repair | fixed by repair | share |
|---|---|---|---|
| S | 47 | 10 | 21% |
| L | 34 | 27 | 79% |
