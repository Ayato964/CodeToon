import numpy as np
class SoftmaxWithLoss:
  def __init__(self):
    self.params = []
    self.grads = []

  def forward(self, x):
    return self.softmax(x)

  def softmax(self, x):
    if x.ndim == 2:
      x = x - x.max(axis = 1, keepdims = True)
      x = np.exp(x)
      x /= x.sum(axis = 1, keepdims = True)
    elif x.ndim == 1:
      x = x - np.max(x)
      x = np.exp(x) / np.sum(np.exp(x))
    
    return x
  
  def backward(self, discount_epr, epdlogp):
    discount_epr = discount_epr - np.mean(discount_epr)
    discount_epr /= np.std(discount_epr)

    endlogp *= discount_epr
    return endlogp
  
