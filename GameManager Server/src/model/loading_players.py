'''
Created on Mar 1, 2018

@author: Bryan, Caleb, Dallas, Josh
'''

def __init__(self,gameName ):
    if(gameName == None):
        raise ValueError('Game error must exist')
    self._gameName = gameName
    