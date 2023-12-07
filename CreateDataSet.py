import numpy as np
import matplotlib.pyplot as plt

# Define the range for theta
theta_range = np.linspace(0, 12 * np.pi, 1000)

# Calculate r for each theta
r_values = theta_range + 4

# Create a dataset with theta and r values
dataset = np.column_stack((theta_range, r_values))

# Optionally, you can plot the polar coordinates
plt.polar(theta_range, r_values)
plt.title(r'$r = \theta + 4$')
plt.show()

# Save the dataset to a CSV file
np.savetxt('polar_dataset.csv', dataset, delimiter=',', header='Theta,R', comments='')

# Display the first few rows of the dataset
print("Generated dataset:")
print("Theta\tR")
print(dataset[:5])