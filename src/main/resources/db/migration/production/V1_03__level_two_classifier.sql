-- Updates all Competitors classifieds to level two
-- @param opi_year as year in which the OPI is being applied
-- @param percentage_considered as percetile between 0 and 1
CREATE OR REPLACE FUNCTION levelTwoClassifier(opi_year INTEGER, percentage_considered FLOAT(4)) RETURNS VOID AS $levelTwoClassifier$
DECLARE
    classifieds CURSOR IS
        SELECT id FROM tb_competitor
        WHERE year = opi_year
        ORDER BY score_level_one DESC
        LIMIT (SELECT CAST(COUNT(*) * percentage_considered AS INTEGER) AS PERCENTILE FROM tb_competitor);
BEGIN
    FOR classified IN classifieds LOOP
        UPDATE tb_competitor SET competitor_level = 'TWO' WHERE id = classified;
    END LOOP;
END;
$levelTwoClassifier$ LANGUAGE plpgsql;
