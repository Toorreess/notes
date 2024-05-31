from sklearn.tree import DecisionTreeClassifier, plot_tree
from sklearn.model_selection import KFold, train_test_split
from sklearn.metrics import accuracy_score

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

from ucimlrepo import fetch_ucirepo 
  
# fetch dataset 
tic_tac_toe_endgame = fetch_ucirepo(id=101) 
  
# data (as pandas dataframes) 
X = pd.DataFrame(tic_tac_toe_endgame.data.features)
y = pd.DataFrame(tic_tac_toe_endgame.data.targets)

# Transform value 'o' to 0, 'x' to 1, 'b' to -1
X = X.replace('o', 0)
X = X.replace('x', 1)
X = X.replace('b', -1)

# Transform value 'positive' to 1, 'negative' to 0
y = y.replace('positive', 1)
y = y.replace('negative', 0)


# Exercise 1: Classify the data with a decision tree classifier. You must find out the best hyperparameters and plot the best decision tree.
# * Split the dataset into training and testing sets
kf = KFold(n_splits=10, shuffle=True, random_state=42)

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# * Create the model
model = DecisionTreeClassifier(max_depth=10)

# * Train the model
model.fit(X_train, y_train)

# * Evaluate model
prediction = model.predict(X_test)

accuracy = accuracy_score(y_true=y_test, y_pred=prediction)


# * Plot the decision tree
plot_tree(model, feature_names=X.columns, class_names=tic_tac_toe_endgame.data.target_names, filled=True)
# plt.title("Decision Tree", size=20, color='red')
plt.show()

# Excercise 2 : Classify the data with a multilayer perceptron classifier. You must find out the best architecture (number of layers, number of hidden layers, activation functions) and the best hyperparameters

