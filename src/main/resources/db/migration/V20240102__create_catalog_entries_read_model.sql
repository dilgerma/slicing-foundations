CREATE TABLE IF NOT EXISTS public.catalog_entries_read_model_entity (
    "item_id" VARCHAR(255) NOT NULL,
    "title" VARCHAR(255),
    PRIMARY KEY ("item_id")
);

CREATE INDEX IF NOT EXISTS idx_catalog_entries_title ON public.catalog_entries_read_model_entity("title");