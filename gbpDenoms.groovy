def denoms = [["2P",2.00],["1P",1.00],["50p",0.50],["20p",0.20],["10p",0.10],["5p",0.05],["2p",0.02],["1p",0.01]]
def solutions = []
findSolutions = { solution, list, amount ->
  if ((list[0][1] - amount) == 0.00) {
    solution.push(list[0][0])
    solutions.push(solution)
  }
  else if (list[0][1] > amount) {
    return
  }
  else {
    solution.push(list[0][0])
    // System.err.println("Solution: "+solution)
    findSolutions(solution.clone(), list, amount - list[0][1])
    if (list.size() > 1) {
      findSolutions(solution.clone(), list[1..list.size()-1].asList(), (amount - list[0][1]))
    }
  }
}

findAllSolutions = { inputDenoms ->
  findSolutions([], inputDenoms, 2.00)
  if(inputDenoms.size() > 1) {
    findAllSolutions(inputDenoms[1..inputDenoms.size()-1])
  }
}

findAllSolutions(denoms)
solutions
