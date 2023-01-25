import numpy as np
import pickle
from RMSProp import RMSProp
from ReLU import ReLU
from Softmax import SoftmaxWithLoss
from FullyConnected import FullyConnected

class network:
  def __init__(self, input_size, middle_size, output_size):
    I, M, O = input_size, middle_size, output_size
    w1 = np.random.randn(I, M) / np.sqrt(I)
    w2 = np.random.randn(M, O) / np.sqrt(M)

    #いじるところ
    self.gamma = 0.3
    self.decay_rate = 0.6
    self.lr = 0.001
    self.optimizer = RMSProp(lr = self.lr, decay_rate = self.decay_rate)

    self.epsilon = 0
    self.drs = []
    self.dlogps = []

    self.layers = [FullyConnected(w1), ReLU(), FullyConnected(w2)]

    self.loss_layer = SoftmaxWithLoss()

    self.params, self.grads, self.rmsprop = [], [], []

    for layer in self.layers:
      self.params += layer.params
      self.grads += layer.grads
      self.rmsprop += layer.rmsprop
    

  def forward(self, x):
    for layer in self.layers:
      x = layer.forward(x)
    return self.loss_layer.forward(x)

  def backward(self, x):
    epdlogp = np.vstack(self.dlogps)
    epr = np.vstack(self.drs)
    self.dlogps, self.drs = [], []
    discounted_epr = self.discounted_rewards(epr)
    dout = self.loss_layer.backword(discounted_epr, epdlogp)
    for layer in reversed(self.layers):
      dout = layer.backward(dout)
    
  def reset(self):
    self.dlogps, self.drs = [], []
    for layer in self.layers:
      layer.reset()
  
  def discount_rewards(self, r):
    discount_r = np.zeros_like(r)
    running_add = 0
    for t in reversed(range(0, r.size)):
      running_add = running_add * self.gamma + r[t]
      discount_r[t] = running_add
    return discount_r
  
  def update(self):
    self.optimizer.step(self.params, self.grads, self.rmsprop)
  
  def record_reward(self, reward):
    self.drs.append(reward)
  
  def select_action(self, aprob):
    if np.random.random() < self.epsilon:
      action = np.random.choice(range(len(aprob)))
    else:
      action = np.random.choice(range(len(aprob)), p = aprob)
    
    y = [0] * (50 * 4)
    y[action] = 1
    self.dlogps.append(y - aprob)
    return action
  
  def save_model(self, filename):
    with open(filename, "wb") as f:
      pickle.dump(self.params, f)


