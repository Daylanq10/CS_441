import matplotlib.pyplot as plt

courses = ['1', '2', '4', '8', '16', '32', '64']
values = [5.304, 3.922, 3.365, 3.309, 3.629, 3.876, 5.165]

fig = plt.figure(figsize=(10, 5))

# creating the bar plot
plt.bar(courses, values, color='red',
        width=0.4)

plt.xlabel("Threads")
plt.ylabel("Seconds")
plt.title("Concurrent Threads Sorting Execution Time")
plt.show()
