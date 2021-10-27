package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    AStarGraph<Vertex> input;
    Vertex start;
    Vertex end;
    double timeout;
    DoubleMapPQ<Vertex> fringe;
    HashMap<Vertex, Double> distToMap;
    int numStatesExplored;
    SolverOutcome outcome;
    double solutionWeight;
    List<Vertex> solution;
    double timeSpent;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        this.input = input;
        this.start = start;
        this.end = end;
        this.timeout = timeout;
        this.fringe = new DoubleMapPQ<>();
        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        this.distToMap = new HashMap<>();
        distToMap.put(start, 0.0);
        this.numStatesExplored = 0;

        List<Vertex> finalPath = new LinkedList<>();
        while (fringe.size() > 0 && !finalPath.contains(end) && sw.elapsedTime() < timeout) {
            numStatesExplored += 1;
            Vertex removed = fringe.getSmallest();
            finalPath.add(removed);
            relax(removed);
        }
        double endTime = sw.elapsedTime();
        if (finalPath.contains(end)) {
            this.outcome = SolverOutcome.SOLVED;
            this.solution = finalPath;
            this.solutionWeight = distToMap.get(end);
        }else {
            if (endTime >= timeout) {
                this.outcome = SolverOutcome.TIMEOUT;
            }else {
                this.outcome = SolverOutcome.UNSOLVABLE;
            }
            this.solution = new LinkedList<>();
            this.solutionWeight = 0;
        }
        this.timeSpent = sw.elapsedTime();
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }

    private void relax(Vertex v) {
        fringe.removeSmallest();
        List<WeightedEdge<Vertex>> neighbors = input.neighbors(v);
        for (WeightedEdge<Vertex> weightedEdge : neighbors) {
            if (!fringe.contains(weightedEdge.to())) {
                fringe.add(weightedEdge.to(), distToMap.get(weightedEdge.from())
                        + weightedEdge.weight() + input.estimatedDistanceToGoal(weightedEdge.to(), end));
                distToMap.put(weightedEdge.to(), distToMap.get(weightedEdge.from()) + weightedEdge.weight());
            }else {
                if (distToMap.get(weightedEdge.to()) > distToMap.get(weightedEdge.from()) + weightedEdge.weight()) {
                    fringe.changePriority(weightedEdge.to(), distToMap.get(weightedEdge.from())
                            + weightedEdge.weight() + input.estimatedDistanceToGoal(weightedEdge.to(), end));
                    distToMap.put(weightedEdge.to(), distToMap.get(weightedEdge.from()) + weightedEdge.weight());
                }
            }
        }
    }

}
