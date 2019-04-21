(define (problem tracker-bee)

	(:domain Killbee)
	
	(:objects
		b1 - tracker
		p1 p2 - plant
		h - hive
		hloc l1 l2 - location
		s - sector
	)
		
	(:init
		(directly-connected hloc l1)
		(directly-connected l1 l2)
		(directly-connected l2 hloc)
		(at b1 hloc)
		(in b1 h)
		(free-sector s)
		(tracker-ready-to-move b1)
	)
	
	(:goal 	
		(and	(at b1 hloc)
				(in b1 h)
				(sector-tracked s)
		)
	)
)