(ns bookmind.schemas
  (:require
   [reitit.coercion.schema]
   [schema.core :as s]))

(s/defschema Address
  {:street s/Str
   :city (s/enum :tre :hki)})

(s/defschema User
  {:id s/Str
   :name s/Str
   :address Address})