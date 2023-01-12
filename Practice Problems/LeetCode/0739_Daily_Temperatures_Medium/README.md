<h2>739. Daily Temperatures</h2>

Given an array of integers `temperatures` represents the daily temperatures, return _an array `answer` such that `answer[i]` is the number of days you have to wait after the i<sup>th</sup> day to get a warmer temperature._

If there is no future day for which this is possible, keep `answer[i] == 0` instead.

**Example 1:**
```
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
```
---
<h3>Was not given below information when done in mock interview setting</h3>

**Example 2:**
```
Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
```

**Example 3:**
```
Input: temperatures = [30,60,90]
Output: [1,1,0]
```

**Constraints:**
- `1 <= temperatures.length <= 105`
- `30 <= temperatures[i] <= 100`
