(define (domain killbee)
(:requirements :strips :typing)
(:types 
  bee location hive plant sector crop - OBJECT
  tracker fumigator - BEE
)
(:predicates 
  (directly-connected ?l1 - LOCATION ?l2 - LOCATION)
  (at ?b - BEE ?l - LOCATION)
  (in ?b - BEE ?h - HIVE)
  (free-sector ?s - SECTOR)
  (sector-tracked ?s - SECTOR)
  (bee-with-sector ?b - TRACKER ?s - SECTOR)
  (plant-analyzed ?p - PLANT)
  (tracker-ready-to-move ?b - TRACKER)
  (last-plant)
)
(:action fly-to-first-plant
:parameters (?b - TRACKER ?h - HIVE ?hivelocation - LOCATION ?l - LOCATION ?s - SECTOR)
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
:parameters (?b - TRACKER ?h - HIVE ?l - LOCATION ?s - SECTOR)
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
:parameters (?b - TRACKER ?l - LOCATION ?s - SECTOR ?p - PLANT ?h - HIVE)
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
:parameters (?b - TRACKER ?from - LOCATION ?target - LOCATION ?s - SECTOR ?p - PLANT ?h - HIVE)
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
:parameters (?b - TRACKER ?l - LOCATION ?s - SECTOR ?p - PLANT ?h - HIVE)
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
:parameters (?b - TRACKER ?from - LOCATION ?target - LOCATION ?s - SECTOR ?h - HIVE)
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