( (domain Usman)
(:requirements :typing :durative-actions)
(:durative-action OP_move
:parameters (?a ?b )
:condition((agent_in ?a))
:duration (= ?duration 0)

)
)
(:durative-action OP_watchTV
:parameters (?c )
:condition((and(agent_in r2)(tv_status on)))
:duration (= ?duration 2)

)
)
(:durative-action OP_TurnOnTv
:parameters (?c )
:condition((and(agent_in r2)(tv_status off)))
:duration (= ?duration 0)

)
)

)