package com.ingconti;

import java.util.Locale;

enum DinnerPhase implements Comparable<DinnerPhase>  {
    UNKNOWN,
    ENTREE,
    MAIN_COURSE,
    SECOND_COURSE,
    DESSERT,
    THE_END_OF_LUNCH;

    DinnerPhase next(){

        switch (this){
            case ENTREE:
                return MAIN_COURSE;

            case MAIN_COURSE:
                return  SECOND_COURSE;

            case SECOND_COURSE:
                return DESSERT;

            case DESSERT:
                return THE_END_OF_LUNCH;

            case THE_END_OF_LUNCH:
                return THE_END_OF_LUNCH;
        }

        return UNKNOWN;
    }

    static DinnerPhase fromString(String s){

        switch (s.toUpperCase().charAt(0)){
            case 'E':
                return ENTREE;
            case 'M':
                return MAIN_COURSE;
            case 'S':
                return SECOND_COURSE;
            case 'D':
                return DESSERT;
            case 'T':
                return THE_END_OF_LUNCH;
            case 'U':
                return UNKNOWN;
        }

        return UNKNOWN;

    }

    @Override
    public String toString() {
        switch (this){
            case ENTREE:
                return "ENTREE";

            case MAIN_COURSE:
                return "MAIN COURSE";

            case SECOND_COURSE:
                return "SECOND COURSE";

            case DESSERT:
                return "DESSERT";

            case THE_END_OF_LUNCH:
                return "END OF YOUR LUNCH!";
        }

        return "UNKNOWN";
    }



}

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
