'''
Created on Mar 1, 2018

@author: Bryan, Dallas, Josh, Caleb
@version: spring 2018
'''
class UserManager:
    '''TODO
    username: if '''
    def _init_(self, username, completedGames = None, inProgress = None, currentGame = None) :
        if(username == None):
            raise ValueError("username cannot be none")
        self._username = username
        if(completedGames == None):
            self._completedGames = []
        else:
            self._completedGames= completedGames
        if(inProgress == None):
            self._inProgress = []
        else:
            self._inProgress= inProgress
        if(currentGame == None):
            self._currentGame = None
        else:
            self._currentGame= currentGame
            
    def getUsername(self):
        return self._username
    def getCompletedGames(self):
        return self._completedGames
    def getInProgressGames(self):
        return self._inProgress
    def getCurrentGame(self):
        return self._currentGame
    def setCurrentGame(self, currGame):
        if (currGame == None):
            raise ValueError("Current game must exist")
        self._currentGame = currGame
    def setInProgressGames(self, games):
        self._inProgress = games
    def setUsername(self, username):
        if(username == None):
            raise ValueError("Invalid username")
        if(username == ""):
            raise ValueError("Username cannot be empty")
        self._username = username
    def addCompletedGame(self, game):
        if(game == None):
            raise ValueError("Game must exist")
        if(game in self._completedGames):
            self._completedGames.remove(game)
        if(game in self._inProgress):
            self._inProgress.remove(game)
        #TODO completed status
        self._completedGames.append(game)
        