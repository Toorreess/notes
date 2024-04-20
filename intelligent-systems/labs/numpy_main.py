import numpy as np
import math


# * Excercise 1: Write a program that creates a unidimensional array with the positive integers smaller than 100 that are multiples of 4.
def f1():
    e1 = np.arange(1, 101)
    return e1[e1 % 4 == 0]


# * Excercise 2: Write a program that creates a bidimensional array of 5 rows and 4 columns with the integers from 0 to 19.
def f2():
    return np.arange(20).reshape(5, 4)


# * Excercise 3: Write a program that inverts the order of the elements of a unidimensional array.
def f3(a: np.ndarray):
    return np.flip(a)
    

# * Excercise 4: Write a program that inverts the order of the rows of a bidimensional array.
def f4(a: np.ndarray):
    return np.flip(a, axis=1)


# * Excercise 5: Write a program that computes the mean of the elements of each column of a bidimensional array.
def f5(a: np.ndarray):
    return np.mean(a, axis=0)


# * Excercise 6: Write a program that reshapes a unidimensional array into a bidimensional array with 4 rows and 3 columns.
def f6(a):
    return a.reshape(4, 3)


# * Excercise 7: Write a program that, given a unidimensional array, checks whether it can be reshaped to a
# * bidimensional array with the same number of rows and columns, i.e., a square matrix.
# * In case that it is possible, it must print the square matrix. Otherwise, it must print an informative message.
def f7(a: np.ndarray):
    n = math.sqrt(a.size)

    if not n.is_integer():
        return ("[*] ERROR: The array passed cannot be reshaped" + 
                "into a square matrix.")
    
    return a.reshape(int(n), int(n))


# * Excercise 8: Write a program that, given a bidimensional array, finds the maximum of each row
def f8(a: np.ndarray):
    return a.max(axis=1)


# * Excercise 9: Write a program that, given a unidimensional array, finds the number of occurrences of each of its unique values.
def f9(a: np.ndarray):
    keys, values = np.unique(a, return_counts=True)

    return dict(zip(keys, values))


# * Excercise 10: Normalize a bidimensional array with 4 rows and 3 columns by subtracting the mean and dividing by the standard deviation on each column.
def f10(a: np.ndarray):
    means = np.reshape(np.mean(a, axis=0), (1,3))
    std = np.reshape(np.std(a, axis=0), (1,3))
    
    return ((a - means) / std)


# * Excercise 11: Normalize a bidimensional array with 4 rows and 3 columns by subtracting the mean and dividing by the standard deviation on each row.
def f11(a: np.ndarray):
    means = np.reshape(np.mean(a, axis=1), (4,1))
    std = np.reshape(np.std(a, axis=1), (4,1))
    
    return ((a - means) / std)


# * Excercise 12: Write a program that, given a bidimensional array, finds the indices (rows and columns) of the minimum and maximum elements of the array.
def f12(a: np.ndarray):    
    min_indices = np.unravel_index(np.argmin(a), a.shape)
    max_indices = np.unravel_index(np.argmax(a), a.shape)
    
    return min_indices, max_indices


# * Excercise 13: Write a program that sorts the rows of a bidimensional array according to the values of the first column.
def f13(a: np.ndarray):    
    sorted_indices = np.argsort(a[..., 0])  
    # sorted_indices = np.argsort(a[:, 0]) # Is also valid
    
    return a[sorted_indices]

print(f13(np.array([[4, 67],[1, 4532],[3, 1],[10,11]])))
# * Excercise 14: Write a program that generates a bidimensional array with 7 rows and 5 columns
# * randomly according to the normal distribution, and then sets to zero all negative elements.
def f14():
    a = np.random.normal(size=(7,5))
    a[a<0] = 0
    
    return a


# * Excercise 15: Write a program that, given a unidimensional array and a positive integer k, finds the indices of the k largest values of the array.
def f15(a: np.ndarray, k: int):    
    return np.argsort(a)[-k:]


# * Excercise 16: Write a program that generates a bidimensional array with 6 rows and 7 columns randomly according to the uniform distribution between 0 and 1,
# * and then sets to zero the two first columns and sets to one the three last columns.
def f16():
    a = np.random.uniform(size=(6, 7))
    a[:, :2] = 0
    a[:, -3:] = 1
    
    return a
