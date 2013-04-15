(ns sicp.chapter-2-1.exercise-02
  (:use clojure.test))

(defn average [& values]
  (/ (apply + values)
     (count values)))

(defrecord Point [x y])

(defprotocol Line
  (midpoint [this]))

(defrecord Segment [start end] Line
  (midpoint [this]
    (Point. (average (.x (.start this)) (.x (.end this)))
            (average (.y (.start this)) (.y (.end this))))))

(deftest exercise-02
         (is (= (Point. 1.5 2.0) (.midpoint (Segment. (Point. 0.0 0.0) (Point. 3.0 4.0)))))
         (is (= (Point. 5.0 10.0) (.midpoint (Segment. (Point. -5.0 -5.0) (Point. 15.0 25.0)))))
         (is (= (Point. -1.5 -1.5) (.midpoint (Segment. (Point. 7.0 -10.0) (Point. -10.0 7.0))))))
