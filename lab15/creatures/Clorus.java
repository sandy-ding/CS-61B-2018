package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;
    /** probability of taking a move when ample space available. */
    private double moveProbability = 0.5;
    /** fraction of energy to retain when replicating. */
    private double repEnergyRetained = 0.5;
    /** fraction of energy to bestow upon offspring. */
    private double repEnergyGiven = 0.5;

    private String preyName = "plip";

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    private void energyCheck() {
        if (energy < 0) {
            energy = 0;
        }
    }

    public void move() {
        energy -= 0.03;
        energyCheck();
    }

    public void stay() {
        energy -= 0.01;
        energyCheck();
    }

    public Clorus replicate() {
        double childEnergy = energy * repEnergyGiven;
        energy *= repEnergyRetained;
        return new Clorus(childEnergy);
    }

    /** Clorus take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, if any Plips are seen, ATTACK randomly.
     *  3. Otherwise, if energy >= 1, REPLICATE to a random empty square.
     *  4. Otherwise, MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        // 1.STAY
        if (empties.size() == 0 ) {
            return new Action(Action.ActionType.STAY);
        }
        // 2.ATTACK
        List<Direction> preies = getNeighborsOfType(neighbors, preyName);
        if (preies.size() != 0 ) {
            Direction attackDir = HugLifeUtils.randomEntry(preies);
            return new Action(Action.ActionType.ATTACK, attackDir);
        }
        // 3. REPLICATE
        if (energy >= 1) {
            Direction replicateDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, replicateDir);
        }
        Direction moveDir = HugLifeUtils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, moveDir);
    }
}
