import numpy as np
class FullyConnected:
  def __init__(self, w):
    self.params = [w]
    self.grads = [np.zeros_like(w)]
    self.rmsprop = [np.zeros_like(w)]
    self.x = []
  
  def forward(self, x):
    w = self.params[0]
    self.x = np.vstack([self.x, np.array(x)]) if len(self.x) != 0 else np.array(x)
    return np.dot(x, w)
  
  def backward(self, dout):
    w = self.params[0]
    dx = np.dot(dout, w.T)
    dw = np.dot(self.x.T, dout)
    self.grads[0] += dw
    self.x = []

    return dx
  
  def reset(self):
    self.x = []