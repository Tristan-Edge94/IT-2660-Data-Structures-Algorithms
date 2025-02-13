## IT-2660 - Lab 2
### Instructions: In Main.java, you'll add all of the code for the following steps in the methods linkedList(), queue(), and stack(). Leave the comments in the code and add your code just after each comment.

#### In linkedList()

1. Create a LinkedList<String> object called progLanguages
2. Add elements to progLanguages: "Java", "Python", "JavaScript", "C++"
3. Remove the element "C++" from the list using .remove()
4. Add an element "HTML" at index 2.
5. Iterate over progLanguages and use println() to output each element. You must create an Iterator<string> and use hasNext(), and next() similar to the example in our book.

#### In queue()

6. Create a Queue<String> called q
7. Add 5 first names to q.
8. Uncomment the following line: System.out.println("Elements of queue: " + q);
9. Remove the head of the queue and assign it to a String variable removedElement. Display the value of "Removed element: " + removedElement
10. View the head of the queue using peek(). Output it's value.
11. Using for(String element : q), output all of the values in the queue

#### In stack()

13. Create a Stack<String> called bookStack
14. Push the following book titles onto bookStack: "Clean Code", "Design Patterns", "Pragmatic Programmer"
15. pop() 1 book off the stack. Display it's value
16. Use the peek() method to view the top book on the stack
17. push() "Web DB Technologies" onto the stack
18. Use the peek() method to view the top book on the stack
19. Search for "Design Patterns" in the stack. Display the results of the search.
20. Call empty(). Output the results
21. Print the titles of all of the books on the stack