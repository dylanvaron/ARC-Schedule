# TreeSet investigation questions
### 1. How does a TreeSet differ from an ArrayList in how it stores and organizes elements?
A TreeSet uses a balanced Binary Search Tree whereas an ArrayList uses a dynamically sizing array. A TreeSet has faster searching and automatically sorts elements whereas an ArrayList typically has faster adding and accessing and allows duplicates.
### 2. What does the floor() method return? What does floor() return if there is no appointment starting at or before the requested time?
floor(request) returns the object that is right before the requested object in the natural order of the Objects. If there is no appiontment at or before the requested time, floor() returns null.
### 3. What does the ceiling() method return? What does ceiling() return if there is no appointment starting at or after the requested time?
ceiling(request) returns the object that is right after the requested object in the natural order of the Objects. If there is no appiontment at or after the requested time, ceiling() returns null.
### 4. Why might these methods be useful in an appointment scheduling program?
If I want to book an appointment at a certian time, we want to know when the appointment happening just before the scheduled time ends and when the appiontment right after begins, so that we don't have overlapping schedules.
### 5. Suppose our calendar contains these appointments:
| Start | End |
| --- | --- |
| 9:00 AM | 9:40 AM |
| 10:15 AM | 11:00 AM |
| 11:30 AM | 12:00 PM |
### If we attempt to schedule an appointment beginning at 10:00 AM, which existing appointments would floor() and ceiling() return, assuming appointments are ordered by start time?
floor() would return the appointment beginning at 9:00 and ceiling would return the appointment beginning at 10:15.

# Short reflection
### 1. Explain how floor() and ceiling() allow your scheduling program to detect overlapping appointments. Why is checking these two appointments sufficient, rather than checking every appointment in the calendar? 
To avoid overlapping when scheduling an appointment, you only need the appointment right before and the appointment right after. Since every appointment before the right-before appointment happens before that appointment, it is impossible for the desired appointment to overlap with any appointments before the right-before appointment without overlapping with the right-before appointment itself. Therefore, only checking the right-before appointment is sufficient for checking for overlap. The same applies to the appintment right after.
### 2. Why is a TreeSet (balanced binary search tree) more efficient than an unsorted ArrayList for finding the appointments immediately before and after a requested time? Compare their Big O time complexities and explain why this matters as the schedule grows. 
In order to have floor() or ceiling() for an ArrayList, you would need to check every single element in the list, which would give both operations a Big O of O(n). However, since a TreeSet uses a balanced binary search tree, it is way more efficient at searching, and by extention floor() and ceiling(), giving both operations a Big O of O(log n). As the schedule gets bigger and is being checked frequently by several students, having a smaller Big O for such fundamental methods becomes necessary.
### 3. Why is it unnecessary to save the exact shape of TreeSet’s internal tree?
A TreeSet's internal tree is a balanced binary search tree. Since the tree is self balancing, it is unecessary to save the exact shape of the internal tree, since the tree will just balance itself anyways.