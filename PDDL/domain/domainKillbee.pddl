(define (domain killbee)
(:requirements :strips :typing :multi-agent)
(:types 
  bee location hive plant sector - object
  tracker fumigator - bee
)
(:predicates 
  (directly-connected ?l1 - location ?l2 - location)
  (at ?b - bee ?l - location)
  (in ?b - bee ?h - hive)
  (free-sector ?s - sector)
  (sector-tracked ?s - sector)
  (bee-with-sector ?b - tracker ?s - sector)
  (plant-analyzed ?p - plant)
  (tracker-ready-to-move ?b - tracker)
  (last-plant)
)
(:action fly-to-first-plant
:parameters (?b - tracker ?h - hive ?hivelocation - location ?l - location ?s - sector)
:precondition 
  (and (in ?b ?h)
  (directly-connected ?hivelocation ?l)
  (bee-with-sector ?b ?s))
:effect 
  (and (not (in ?b ?h))
  (at ?b ?l)
  (not (tracker-ready-to-move ?b)))
)
(:action assign-sector
:parameters (?b - tracker ?h - hive ?l - location ?s - sector)
:precondition 
  (and (in ?b ?h)
  (free-sector ?s)
  (not (bee-with-sector ?b ?s))
  (not (sector-tracked ?s)))
:effect 
  (and (not (free-sector ?s))
  (bee-with-sector ?b ?s))
)
(:action analyze-plant
:parameters (?b - tracker ?l - location ?s - sector ?p - plant ?h - hive)
:precondition 
  (and (at ?b ?l)
  (not (plant-analyzed ?p))
  (not (in ?b ?h)))
:effect 
  (and (tracker-ready-to-move ?b)
  (plant-analyzed ?p)
  (last-plant))
)
(:action go-to-next-plant
:parameters (?b - tracker ?from - location ?target - location ?s - sector ?p - plant ?h - hive)
:precondition 
  (and (directly-connected ?from ?target)
  (not (sector-tracked ?s))
  (tracker-ready-to-move ?b)
  (not (plant-analyzed ?p))
  (not (in ?b ?h)))
:effect 
  (and (at ?b ?target)
  (not (tracker-ready-to-move ?b))
  (not (at ?b ?from)))
)
(:action analyze-last-plant
:parameters (?b - tracker ?l - location ?s - sector ?p - plant ?h - hive)
:precondition 
  (and (at ?b ?l)
  (last-plant)
  (not (plant-analyzed ?p))
  (not (tracker-ready-to-move ?b))
  (not (in ?b ?h)))
:effect 
  (and (tracker-ready-to-move ?b)
  (plant-analyzed ?p)
  (sector-tracked ?s))
)
(:action back-home
:agent b - tracker
:parameters (?b - tracker ?from - location ?target - location ?s - sector ?h - hive)
:precondition 
  (and (tracker-ready-to-move ?b)
  (sector-tracked ?s)
  (directly-connected ?from ?target)
  (not (in ?b ?h)))
:effect 
  (and (at ?b ?target)
  (not (tracker-ready-to-move ?b))
  (not (at ?b ?from))
  (not (bee-with-sector ?b ?s))
  (in ?b ?h))
)
)