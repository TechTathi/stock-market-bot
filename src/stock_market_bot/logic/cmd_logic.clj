(ns stock-market-bot.logic.cmd-logic
  (:require [stock-market-bot.service :as service]
            [clojure.string           :as str]))


(def wallet (atom #{}))
(defn add-stock-wallet
  [stock]
  (swap! wallet conj stock))

(defn cmd-help [prefix text send-tx! _ _]
  (send-tx!
    "How to use this bot:"
    (apply str
           (map #(str "\n" prefix %)
                ["stock stock code (ex. AMZN)\n Here you can have real time details about a specific stock"
                 "wallet stock code (ex. AMZN)\n Here you can add a stock to your wallet"]))))

(defn cmd-stock! [text send-tx! send-md! _]
  (let [[_ stock] (str/split text #" ")]
    (send-tx! (service/common-stokes-url-call stock))))

(defn cmd-wallet-add! [text send-tx! send-md! _]
  (let [[_ stocks] (str/split text #" ")]
    (send-tx! (str "Your wallet" (add-stock-wallet stocks))))

  )