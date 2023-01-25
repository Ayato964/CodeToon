import numpy as np
class ReLU:
  def __init__(self):
    self.params = []
    self.grads = []
    self.rmsprop = []
    self.mask = []
  
  def forward(self, x):
    mask = (x <= 0)
    if len(self.mask) != 0:
      self.mask = np.vstack([self.mask, mask])
    else:
      self.mask = mask
    out = x
    out[mask] = 0
    return out
  
  def backward(self, dout):
    dout[self.mask] = 0
    dx = dout
    self.mask = []
    return dx
  
  def reset(self):
    self.mask = []
    
