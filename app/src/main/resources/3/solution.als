
/*
 * Solutions to lesson3 exercise
 */

a) named stations are on exactly the lines as shown in graphic
  Stanmore in (JubileeStation - CentralStation) - CircleStation
  BakerStreet in (JubileeStation & CircleStation) - CentralStation
  Epping in (CentralStation - JubileeStation) - CircleStation

b) no station (including those unnamed) is on all three lines
  no (JubileeStation & CentralStation & CircleStation)

c) the Circle line forms a circle
  all s: CircleStation {
    one s.circle
    CircleStation in s.^circle
  }

d) Jubilee is a straight line starting at Stanmore
  JubileeStation in Stanmore.*jubilee
  all s: JubileeStation {
    lone s.jubilee
    s not in s.^jubilee
  }

e) there's a station between Stanmore and BakerStreet
  let reach = ^jubilee | some Stanmore.reach & reach.BakerStreet

f) it is possible to travel from BakerStreet to Epping
  Epping in BakerStreet.^(jubilee + central + circle)
 