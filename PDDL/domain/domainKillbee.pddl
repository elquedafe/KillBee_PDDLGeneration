(define (domain killbee)
(:requirements :strips :typing)
(:types 
  bee location hive plant sector - OBJECT
  tracker fumigator - BEE
)
(:predicates 
  (directly-connected ?l1 - location ?l2 - location)
  (at ?b - bee ?l - location)
  (in ?b - bee ?h - hive)
  (free ?s - sector)
)
)