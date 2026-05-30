# 268 — Missing Number

**Difficulty:** Easy
**Topic:** Arrays / Bit Manipulation / Math
**Link:** https://leetcode.com/problems/missing-number/

## Problem
Array of `n` distinct numbers from range `[0, n]`. Exactly one number is missing. Return it.

## Approaches

| # | Approach     | Time      | Space | Notes                              |
|---|--------------|-----------|-------|------------------------------------|
| 1 | Brute force  | O(n²)     | O(n)  | Nested scan, easy to reason about  |
| 2 | Sorting      | O(n log n)| O(1)* | Compare index to value             |
| 3 | Marker array | O(n)      | O(n)  | Place each num at its index        |
| 4 | XOR          | O(n)      | O(1)  | Best — `a ^ a = 0`, only missing survives |
| 5 | Gauss sum    | O(n)      | O(1)  | `n*(n+1)/2 − sum(nums)` (overflow risk for large n) |

\* sort modifies input

## Key insight (XOR)
XOR all indices `[0..n]` with all values in `nums`. Every paired number cancels (`x ^ x = 0`), leaving only the missing index. Initialize `result = n` to skip the explicit second loop.

## Pitfalls
- Off-by-one: range is `[0, n]` inclusive, so array of length `n` has `n+1` possible values.
- Gauss sum overflows when `n` is large — XOR avoids this.
- Don't return `-1` from brute force; the problem guarantees a missing number exists.

## Pattern
"Find the duplicate / missing in [0, n]" → think XOR or Gauss sum first, marker array second.
