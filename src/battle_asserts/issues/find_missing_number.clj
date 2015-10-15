(ns battle-asserts.issues.find-missing-number
  (:require [clojure.test.check.generators :as gen]
            [faker.generate :as faker]
            [battle-asserts.utility :as utility]))

(def level :easy)

(def description "In the sequence of integers from 1 to N in a random order,
                 but one of the numbers is missing (the others are found exactly once).
                 N is not known beforehand.
                 Determine the missing number in a single pass.")

(defn arguments-generator []
  (gen/tuple (gen/bind (gen/choose 1 8)
                       #(gen/return (let [coll (range 1 %)]
                                      (utility/drop-nth coll (rand-int (count coll))))))))

(def test-data
  [{:expected 1
    :arguments [[2, 3, 4, 5]]}
   {:expected 2
    :arguments [[1, 3, 4, 5]]}
   {:expected 4
    :arguments [[1, 2, 3, 5]]}])

(defn solution [coll]
  (let [n (inc (count coll))
        sum-seq (* (/ (inc n)
                      2)
                   n)]
    (->> coll
         (reduce +)
         (- sum-seq)
         (int))))
