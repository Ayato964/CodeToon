import numpy as np
class RMSProp:
  def __init__(self, lr, decay_rate = 0.9):
    self.lr = lr
    self.decay_rate = decay_rate
  
  def step(self, params, grads, rmsprop):
    for i in range(len(params)):
      rmsprop[i] = self.decay_rate * rmsprop[i] + (1 - self.decay_rate) * grads[i] ** 2
      params[i] += self.lr * grads[i] / (np.sqrt(rmsprop[i]) + 0.00001)
      grads[i] *= 0
      
    