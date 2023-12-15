data = csvread('D:\Program Files\functionResults.csv');
x = data(:, 1);
y = data(:, 2);
plot(x, y, '-', 'LineWidth', 2);
hold on;

noisefact = 50;
noise = noisefact * randn(size(y));
ynoise = y + noise;
plot(x, ynoise, '-', 'LineWidth', 2);
hold on;
window = 5;
smoothed = movmean(ynoise, window);
plot(x, smoothed, '-', 'Linewidth', 2);
title('y = e + x^{sin(x)}');
xlabel('x');
ylabel('y');
grid on;
