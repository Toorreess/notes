import pandas as pd

df = pd.read_csv("/home/uni/notes/intelligent-systems/labs/bmw.csv")


# * Excercise 1: Show the first 10 samples of the dataset.
def f1():
    return df[0:10]


# * Exercise 2: Obtain the data series corresponding to the year atribute, and then obtain the data type and the number of samples of such series.
def f2():
    return df.get("year")


# * Excercise 3: Obtain the data series corresponding to the mileage atribute, and then select the samples whose position in the series are multiples of 7.
def f3():
    x = df.get("mileage")

    return x[::7]


# * Exercise 4: Obtain the data series corresponding to the mileage atribute, and then select randomly 40% of the samples of the series.
def f4():
    x = df.get("mileage")
    return x.sample(frac=0.4)


# * Exercise 5: Obtain the data series corresponding to the mileage atribute, and then select the samples with a value lower than 20000 of that series.
def f5():
    x = df.get("mileage")
    return x[x < 20000]


# * Exercise 6: Obtain the data series corresponding to the mpg atribute, and then sort the samples of the series.
def f6():
    x = df.get("mpg")
    return x.sort_values()


# * Excercise 7. Compute the mean, the standard deviation, the maximum and the minimum of the engineSize atribute
def f7():
    x = df.get("engineSize")
    return x.mean(), x.std(), x.max(), x.min()


# * Excercise 8: Obtain the number of rows and columns of the dataset, and the third sample starting from the end.
def f8():
    return df.shape, df.iloc[-3]


# * Excercise 9: Extract the mileage, price and mpg atributes to a new DataFrame, and then choose 20% of the samples at random.
def f9():
    new_df = df[["mileage", "price", "mpg"]].copy()
    return new_df.sample(frac=0.2)

print(f9())
# * Excercise 10: Obtain the samples whose value of the mileage atribute is lower than 10000 and the value of the mpg atribute is higher than 40
def f10():
    return df[(df.get("mileage") < 10000) & (df.get("mpg") > 40)]


# * Excercise 11: Modify the values of the model atribute so that the " x Series" values are changed to "Series x", where x is a number between 1 and 9.
def f11():
    df['model'] = df['model'].replace(r'(\d) Series', 
                                    r'Series \1', 
                                    regex=True)
    return df


# * Exercise 12: Insert a new sample with the following values: model=" 3 Series", year=2023, price = 22572, transmission = "Automatic", mileage = 74120, fuelType = "Diesel", tax = 160, mpg = 58.4, engineSize = 2.0}
def f12():
    new_df = pd.DataFrame({
        "model": ["3 Series"],
        "year": [2023],
        "price": [22572],
        "transmission": ["Automatic"],
        "mileage": [74120],
        "fuelType": ["Diesel"],
        "tax": [160],
        "mpg": [58.4],
        "engineSize": [2.0],
    })
    return pd.concat([df, new_df], ignore_index=True)


# * Excercise 13: Convert the DataFrame into a numpy ndarray and print out the data type of the obtained ndarray
def f13():
    return df.to_numpy()


# * Excercise 14: Compute for each sample the average mileage per year.
def f14():
    return df["mileage"] / (2024 - df["year"])
