READ ME!

to solve problem of a STATE AND network, try {
    this simple sample!
}

// no catch! ;)


Our FSM describes a lunch.
It starts from ENTREE and evolves sending "g" (GO!) command from client on TCP.
States are:

    UNKNOWN,
    ENTREE,
    MAIN_COURSE,
    SECOND_COURSE,
    DESSERT,
    THE_END_OF_LUNCH;

Allowed commnads are:

- "G" (GO!)
- every initial char of every state, i.e. "E", "M"..."T"
- "P" to pay.

You cannot go back when specifying state.

NOTE: you cannot finish lunch with out PAYING!
You can any state you are.

--
to test against console:

/usr/bin/nc 127.0.0.1 1234
and type in console: server will receive and send back some info. (in server more details about internal state)


