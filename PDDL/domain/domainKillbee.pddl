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
  (crop-tracked ?c - CROP)
)
(:action fly-to-first-plant
:parameters (?b - TRACKER ?h - HIVE ?l - LOCATION ?s - SECTOR)
:precondition 
  (and (in ?b ?h)
  (directly-connected ?h ?l)
  (free-sector ?s))
:effect 
  (and (not (in ?b ?h))
  (at ?b ?l))
)
)