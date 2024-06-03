import logging

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn.discriminant_analysis import StandardScaler
from sklearn.metrics import accuracy_score
from sklearn.model_selection import GridSearchCV, KFold, train_test_split
from sklearn.neural_network import MLPClassifier
from sklearn.tree import DecisionTreeClassifier, plot_tree
from ucimlrepo import fetch_ucirepo

# fetch dataset
tic_tac_toe_endgame = fetch_ucirepo(id=101)

# data (as pandas DataFrames)
X = pd.DataFrame(tic_tac_toe_endgame.data.features)
y = pd.DataFrame(tic_tac_toe_endgame.data.targets)

# Transform value 'o' to 0, 'x' to 1, 'b' to -1
X = X.replace("o", 0)
X = X.replace("x", 1)
X = X.replace("b", -1)

# Transform value 'positive' to 1, 'negative' to 0
y = y.replace("positive", 1)
y = y.replace("negative", 0)


# Exercise 1: Classify the data with a decision tree classifier. You must find out the best hyperparameters and plot the best decision tree.

# * Split the data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

kf = KFold(n_splits=10)
kf.get_n_splits(X)

# * Define the hyperparameters to search
param_grid = {
    "max_depth": [i for i in range(10)],
    "min_samples_split": [i for i in range(10)],
    "min_samples_leaf": [i for i in range(10)],
}

# * Perform grid search to find the best hyperparameters
grid_search = GridSearchCV(DecisionTreeClassifier(), param_grid, cv=10)
grid_search.fit(X_train, y_train)

# * Get the best hyperparameters
best_params = grid_search.best_params_

logging.info(f" Best params: {best_params}")

# * Train the decision tree classifier with the best hyperparameters
best_dtc = DecisionTreeClassifier(**best_params)
best_dtc.fit(X_train, y_train)

# * Predict the test set labels
y_pred = best_dtc.predict(X_test)

# * Calculate the accuracy of the classifier
accuracy = accuracy_score(y_test, y_pred)
logging.info(f" accuracy:{accuracy}")

# * Plot the best decision tree
plt.figure(figsize=(15, 15))
plot_tree(
    best_dtc,
    max_depth=3,
    label="root",
    feature_names=X.columns,
    class_names=["negative", "positive"],
    filled=True,
    rounded=True,
    fontsize=7,
)

plt.show()


# Exercise 2 : Classify the data with a multilayer perceptron classifier. You must find out the best architecture (number of layers, number of hidden layers, activation functions) and the best hyperparameters
# * Define the hyperparameters to search
param_grid = {
    "hidden_layer_sizes": [(i,) for i in range(20)],
    "activation": ["relu", "tanh", "logistic"],
    "alpha": [0.01],
}

# Scale the data
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.transform(X_test)

# Flatten y_train before fitting the model
y_train = np.ravel(y_train)

# Perform grid search to find the best hyperparameters
grid_search = GridSearchCV(
    MLPClassifier(max_iter=2000), param_grid, cv=10, scoring="accuracy"
)
grid_search.fit(X_train_scaled, y_train)

# Get the best hyperparameters
best_params = grid_search.best_params_

# Train the MLP classifier with the best hyperparameters
best_mlp = MLPClassifier(**best_params)
best_mlp.fit(X_train_scaled, y_train)

# Predict the test set labels
y_pred = best_mlp.predict(X_test_scaled)

# Calculate the accuracy of the classifier
accuracy = accuracy_score(y_test, y_pred)
logging.info(f" accuracy:{accuracy}")
