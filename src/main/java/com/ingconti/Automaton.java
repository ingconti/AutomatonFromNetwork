package com.ingconti;

import java.util.Locale;

enum DinnerPhase {
    UNKNOWN,
    ENTREE,
    MAIN_COURSE,
    SECOND_COURSE,
    DESSERT,
    END_OF_LUNCH;

    DinnerPhase next(){

        switch (this){
            case ENTREE:
                return MAIN_COURSE;

            case MAIN_COURSE:
                return  SECOND_COURSE;

            case SECOND_COURSE:
                return DESSERT;

            case DESSERT:
                return END_OF_LUNCH;

            case END_OF_LUNCH:
                return END_OF_LUNCH;
        }

        return UNKNOWN;
    }

    static DinnerPhase fromString(String s){

        switch (s.toUpperCase().charAt(0)){
            case 'M':
                return MAIN_COURSE;
            case 'S':
                return SECOND_COURSE;
            case 'D':
                return DESSERT;
            case 'E':
                return END_OF_LUNCH;
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

            case END_OF_LUNCH:
                return "END OF LUNCH!";
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
        if (state == DinnerPhase.DESSERT && !paid) {
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

        if (state.ordinal() < DinnerPhase.END_OF_LUNCH.ordinal()) {
            state = state.next();
            return true;
        }
        return false;
    }


    public Boolean evolveTo(DinnerPhase toSstate){
        if (canEvolve() == false)
            return false;

        if (toSstate.ordinal() > state.ordinal()) {
            state = toSstate;
            return true;
        }
        return false;
    }



}
