import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

df = pd.read_csv('/home/uni/notes/intelligent-systems/labs/bmw.csv')


# * Excercise 1: Plot the function f(x) = sin(x)+cos(x) in the [0,2*pi] interval.
def f1():
    x = np.linspace(0, 2*np.pi)
    f = np.sin(x) + np.cos(x)

    plt.plot(x, f, label="sin(x) + cos(x)")

    plt.legend()
    plt.title("Exercise 1")
    plt.show()


# * Excercise 2: Plot the functions f(x) = sin(x), g(x)=log(1+x) in the [0,2*pi] interval, considering 100 equally spaced points on such interval.
def f2():
    
    x = np.linspace(0, 2*np.pi, 100)
    f = np.sin(x)
    g = np.log(1 + x)

    plt.plot(x, f, label="sin(x)")
    plt.plot(x, g, label="log(1+x)")

    plt.legend()
    plt.title("Exercise 2")
    plt.show()

# * Excercise 3: Create a bar plot that shows the frequencies of the values of the model attribute in the dataset.
def f3():
    freq = df['model'].value_counts()
    
    plt.barh(freq.index, freq.values)

    plt.title("Exercise 3")
    plt.xlabel("Frequency")
    plt.ylabel("Model")
    plt.show()


# * Excercise 4: Create a scatter plot that shows the values of the price attribute on the horizontal axis, the values of the mileage attribute on the vertical axis, and the values of the mpg attribute as colors.
def f4():
    plt.scatter(df['price'], df['mileage'], c=df['mpg'])
    plt.colorbar()

    plt.title("Exercise 4")
    plt.xlabel("Price")
    plt.ylabel("Mileage")
    plt.show()


# * Excercise 5: Create a histogram that shows the values of the price attribute of the dataset.
def f5():
    data = df['price']

    plt.hist(data, edgecolor='black')

    plt.title("Exercise 5")
    plt.xlabel("Price")
    plt.ylabel("Frequency")
    plt.show()


# * Excercise 6. Use the funcƟon to plot a heatmap that depicts a part of the Mandelbrot fractal. The heatmap must be 600 pixels wide and 400 pixels high. The values of the real part of the complex numbers must range between ‐2 and 1, while the values of the imaginary part must range between ‐1 and 1
# ! Aux function
MAX_ITERATIONS = 80
def mandelbrot(a,b):
    c = complex(a,b)
    z = 0
    n = 0
    while abs(z) <= 2 and n < MAX_ITERATIONS:
        z = z*z + c
        n += 1
    pixel_color = 255 - int(n * 255 / MAX_ITERATIONS)
    return pixel_color

def f6():
    width, height = 600, 400
    real_range = np.linspace(-2, 1, width)
    imaginary_range = np.linspace(-1, 1, height)
    image = np.zeros((height, width))

    for x in range(width):
        for y in range(height):
            a = real_range[x]
            b = imaginary_range[y]
            image[y, x] = mandelbrot(a, b)

    plt.imshow(image)
    plt.title("Exercise 6")
    plt.show()
