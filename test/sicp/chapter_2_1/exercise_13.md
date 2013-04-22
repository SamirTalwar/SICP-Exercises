Exercise 2.13
=============

Given the code in the solution to Exercise 2.12, with a simplified version of the multiplication operation:

    (defmethod arithmetic/* [Interval Interval]
      [x y]
      (Interval/create {:lower (* (:lower x) (:lower y))
                        :upper (* (:upper x) (:upper y))}))

We can see that, given two intervals, A and B, with centres Ac and Bc, and percentage tolerances At and Bt:

    (Ac ± At%) * (Bc ± Bt%)
    = (Ac ± (AcAt)) * (Bc ± (BcBt))
    = (Ac - AcAt)..(Ac + AcAt) * (Bc - BcBt)..(Bc + BcBt)
    = ((Ac - AcAt) * (Bc - BcBt))..((Ac + AcAt) * (Bc + BcBt))
    = (AcBc - AcBcBt - BcAcAt + AcBcAtBt)..(AcBc + AcBcBt + BcAcAt + AcBcAtBt)
    = (AcBc + AcBcAtBt) ± (AcBcBt + BcAcAt)
    = (AcBc + AcBcAtBt) ± (AcBc * (At + Bt))

Given appropriately small tolerances, we can remove any term with At * Bt, as it is negligible, resulting in:

    AcBc ± (AcBc * (At + Bt))
    = AcBc ± (At + Bt)%

Therefore, for sufficiently small percentage tolerances, multiplying two intervals results in an interval with a tolerance of the sum of the two tolerances.
