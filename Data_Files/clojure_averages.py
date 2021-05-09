def average(num_1, num_2, num_3, threads):
    average = (num_1 + num_2 + num_3)/3
    average = average/1000
    average = round(average, 3)
    print("Average for 3 executions for", threads, "threads is -->", average, "seconds.")


average(5973.863353, 4951.558166, 4986.954877, 1)
average(3919.135437, 4199.129202, 3647.223932, 2)
average(3377.504321, 3284.257188, 3432.379745, 4)
average(3288.202482, 3470.345273, 3167.928078, 8)
average(3674.648577, 3572.715804, 3640.237038, 16)
average(3895.554976, 3943.116546, 3788.478915, 32)
average(5351.483071, 5670.039116, 4472.939469, 64)
