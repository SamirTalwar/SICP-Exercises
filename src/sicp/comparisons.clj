(ns sicp.comparisons
  (:require [clojure.contrib.math :as math]))

(defn approximately-within? [tolerance]
   (fn [a b]
     (< (math/abs (- a b)) tolerance)))

(def approximately? (approximately-within? 0.0001))
