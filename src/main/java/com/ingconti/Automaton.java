package com.ingconti;

import java.util.Locale;

public class Automaton {

    private Boolean paid = false;
    private DinnerPhase state = DinnerPhase.ENTREE;

    public DinnerPhase getState(){
        return state;
    }

    private Boolean canEvolve(){
        int currOrd = state.ordinal();
        int dessertOrd = DinnerPhase.DESSERT.ordinal();

        if (currOrd >= dessertOrd && !paid) {
            System.out.println("PAY BEFORE!!!");
            return false;
        }
        return true;
    }

    public void setPaid(){
        paid = true;
    }


    public Boolean evolve() {
        if (canEvolve() == false)
            return false;

        int currOrd = state.ordinal();
        int lastOrd = DinnerPhase.THE_END_OF_LUNCH.ordinal();

        if (currOrd < lastOrd) {
            state = state.next();
            return true;
        }
        return false;
    }


    public Boolean evolveTo(DinnerPhase toSstate){
        if (canEvolve() == false)
            return false;

        int toOrd = toSstate.ordinal();
        int currOrd = state.ordinal();

        if (toOrd>currOrd) {
            state = toSstate;
            return true;
        }
        return false;
    }



}
