# LeetCode — Java

Personal solutions in Java 17, organized by topic with JUnit 5 tests and per-problem notes.

## Layout

```
src/main/java/<topic>/P<num><Name>.java   solutions, grouped by topic
src/test/java/<topic>/P<num><Name>Test.java  JUnit tests
notes/<num>-<kebab-name>.md                  approach + complexity + pitfalls
pom.xml                                      Maven build
```

## Run tests

```powershell
mvn test                                                 # all
mvn -Dtest=P0268MissingNumberTest test                   # one class
mvn -Dtest=P0268MissingNumberTest#xor test               # one method
```

## Progress

| #   | Problem        | Topic  | Difficulty | Notes |
|-----|----------------|--------|------------|-------|
| 268 | Missing Number | arrays | Easy       | [link](notes/0268-missing-number.md) |

## Topics tracked

`arrays` · `strings` · `linkedlist` · `trees` · `graphs` · `dp` · `backtracking` · `heap` · `twopointers` · `bitmanip` · `math`

Add a new topic folder under `src/main/java/` only when you have a problem for it.

## Workflow per problem

1. Read problem, write naive solution first (brute force).
2. Add tests covering: example cases, single element, empty, max size.
3. Optimize. Compare approaches in `notes/`.
4. Commit `feat(<topic>): solve <num> <name>`.

## Common types

`common.ListNode` and `common.TreeNode` match LeetCode's exact signatures — use them directly in linked-list and tree problems.

Test helpers in `common.ListNodes` / `common.TreeNodes` build these from arrays the way LeetCode shows examples:

```java
ListNode head = ListNodes.fromArray(new int[]{1, 2, 3});
assertEquals(List.of(3, 2, 1), ListNodes.toList(reversed));

TreeNode root = TreeNodes.fromLevelOrder(new Integer[]{3, 9, 20, null, null, 15, 7});
assertEquals(List.of(3, 20, 9, 7, 15), TreeNodes.toLevelOrder(inverted));
```

## Solution template

Copy these two files when starting a new problem (replace `<topic>`, `<num>`, `<Name>`):

`src/main/java/<topic>/P<num><Name>.java`
```java
package <topic>;

/** LeetCode <num> — <Name>. https://leetcode.com/problems/<slug>/ */
public class P<num><Name> {
    public <returnType> solve(<args>) {
        // TODO
        return <default>;
    }
}
```

`src/test/java/<topic>/P<num><Name>Test.java`
```java
package <topic>;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class P<num><Name>Test {
    private final P<num><Name> sol = new P<num><Name>();

    @Test
    void example1() {
        assertEquals(<expected>, sol.solve(<input>));
    }
}
```
