CREATE VIEW house AS
SELECT t1.buildYear, t1.dong, t1.apartmentName as aptName, t1.jibun, t1.lng, t1.lat, t2.dongCode, t2.sidoName, t2.gugunName, t2.dongName, t2.dealAmount, t2.dealYear, t2.dealMonth, t2.area, t2.floor
FROM houseinfo as t1 NATURAL JOIN
(SELECT dongCode, sidoName, gugunName, dongName, dealAmount, dealYear, dealMonth, area, floor
FROM dongcode NATURAL JOIN housedeal) as t2;