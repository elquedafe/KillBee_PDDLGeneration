(define (domain killbee)
(:requirements :strips :typing :multi-agent)
(:types 
  bee location - object
  tracker fumigator - bee
  hive plant - location
)
(:predicates 
  (directly-connected ?l1 - location ?l2 - location)
  (at ?b - bee ?l - location)
  (plant-analyzed ?p - plant)
  (tracker-ready-to-move ?b - tracker)
  (first-plant ?b - tracker ?p - plant)
  (fumigator-ready-to-move ?b - fumigator)
  (pesticide-tank-empty ?b - fumigator)
  (infected-location ?b - fumigator ?l - location)
  (healthy-plant ?p - plant)
)
(:action fly-to-first-plant
:agent ?b - tracker
:parameters (?h - hive ?p - plant)
:precondition 
  (and (at ?b ?h)
  (not (at ?b ?p))
  (directly-connected ?h ?p)
  (first-plant ?b ?p)
  (not (plant-analyzed ?p))
  (tracker-ready-to-move ?b))
:effect 
  (and (not (at ?b ?h))
  (at ?b ?p)
  (not (tracker-ready-to-move ?b)))
)
(:action analyze-plant
:agent ?b - tracker
:parameters (?p - plant ?h - hive)
:precondition 
  (and (at ?b ?p)
  (not (at ?b ?h))
  (not (tracker-ready-to-move ?b)))
:effect 
  (and (tracker-ready-to-move ?b)
  (plant-analyzed ?p))
)
(:action go-to-next-plant
:agent ?b - tracker
:parameters (?from - plant ?target - plant ?h - hive)
:precondition 
  (and (directly-connected ?from ?target)
  (tracker-ready-to-move ?b)
  (at ?b ?from)
  (not (at ?b ?target))
  (not (at ?b ?h))
  (not (plant-analyzed ?target))
  (plant-analyzed ?from))
:effect 
  (and (at ?b ?target)
  (not (tracker-ready-to-move ?b))
  (not (at ?b ?from)))
)
(:action tracker-back-home
:agent ?b - tracker
:parameters (?p - plant ?h - hive)
:precondition 
  (and (tracker-ready-to-move ?b)
  (at ?b ?p)
  (not (at ?b ?h)))
:effect 
  (and (at ?b ?h)
  (not (at ?b ?p))
  (not (healthy-plant ?p)))
)
(:action receive-infected-location
:agent ?b - fumigator
:parameters (?h - hive ?p - plant)
:precondition 
  (and (not (fumigator-ready-to-move ?b))
  (directly-connected ?h ?p)
  (at ?b ?h)
  (not (infected-location ?b ?p))
  (pesticide-tank-empty ?b)
  (not (healthy-plant ?p)))
:effect 
  (infected-location ?b ?p)
)
(:action fill-pesticide-tank
:agent ?b - fumigator
:parameters (?h - hive ?p - plant)
:precondition 
  (and (not (fumigator-ready-to-move ?b))
  (pesticide-tank-empty ?b)
  (at ?b ?h)
  (infected-location ?b ?p)
  (not (healthy-plant ?p)))
:effect 
  (and (not (pesticide-tank-empty ?b))
  (fumigator-ready-to-move ?b))
)
(:action go-to-infected-location
:agent ?b - fumigator
:parameters (?h - hive ?p - plant)
:precondition 
  (and (fumigator-ready-to-move ?b)
  (not (pesticide-tank-empty ?b))
  (at ?b ?h)
  (infected-location ?b ?p)
  (not (healthy-plant ?p)))
:effect 
  (and (not (at ?b ?h))
  (at ?b ?p)
  (not (fumigator-ready-to-move ?b)))
)
(:action fumigate
:agent ?b - fumigator
:parameters (?h - hive ?p - plant)
:precondition 
  (and (not (fumigator-ready-to-move ?b))
  (not (pesticide-tank-empty ?b))
  (at ?b ?p)
  (not (at ?b ?h))
  (not (healthy-plant ?p)))
:effect 
  (and (pesticide-tank-empty ?b)
  (fumigator-ready-to-move ?b)
  (not (infected-location ?b ?p))
  (healthy-plant ?p))
)
(:action fumigator-back-home
:agent ?b - fumigator
:parameters (?h - hive ?p - plant)
:precondition 
  (and (fumigator-ready-to-move ?b)
  (pesticide-tank-empty ?b)
  (not (at ?b ?h))
  (not (infected-location ?b ?p))
  (healthy-plant ?p))
:effect 
  (and (at ?b ?h)
  (not (fumigator-ready-to-move ?b)))
)
)