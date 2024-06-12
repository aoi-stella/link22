from google.cloud import bigquery

class BigQueryAPI:
    def __init__(self, project_id):
        self.client = bigquery.Client(project=project_id)
        self.dataset_id = f"{project_id}.dataset"
        
    def create_dataset(self):
        # データセットの存在を確認して作成する
        dataset = bigquery.Dataset(self.dataset_id)
        dataset.location = "US"
        
        try:
            self.client.get_dataset(self.dataset_id)  # データセットが存在するか確認
            print(f"Dataset {self.dataset_id} already exists")
        except Exception:
            dataset = self.client.create_dataset(dataset)  # データセットを作成
            print(f"Created dataset {self.dataset_id}")
    
    def insert_to_row(self, table_id, data):
        # テーブルスキーマを定義する
        schema = [
            bigquery.SchemaField("contents", "STRING", mode="REQUIRED"),
        ]
        
        # テーブルIDを完全修飾で定義する
        table_id = f"{self.dataset_id}.{table_id}"
        
        try:
            self.client.get_table(table_id)  # テーブルが存在するか確認
            print(f"Table {table_id} already exists")
        except Exception:
            table = bigquery.Table(table_id, schema=schema)
            table = self.client.create_table(table)  # テーブルを作成
            print(f"Created table {table_id}")
        
        # データを挿入する
        errors = self.client.insert_rows_json(table_id, data)
        if errors:
            raise RuntimeError(f"Failed to insert rows to BigQuery: {errors}")
        print(f"Inserted rows into table {table_id}")
        
    def clear_table(self, table_id):
        # テーブルIDを完全修飾で定義する
        table_id = f"{self.dataset_id}.{table_id}"
        
        # テーブルの内容をすべて削除するクエリを実行する
        query = f"DELETE FROM `{table_id}` WHERE TRUE"
        job = self.client.query(query)
        
    def classify_articles(self, project_id, table_id):
        client = bigquery.Client(project=project_id)
        dataset_id = f"{project_id}.dataset"
        table_with_tags_id = f"{dataset_id}.{table_id}_with_tags"
        
        query = f"""
        CREATE OR REPLACE TABLE `{table_with_tags_id}` AS
        SELECT
        contents,
        CASE
            WHEN contents LIKE '%exploit%' THEN 'Exploit'
            WHEN contents LIKE '%漏洩%' THEN '事件'
            ELSE 'その他'
        END AS tags
        FROM
        `{dataset_id}.{table_id}`
        """
        
        job = client.query(query)
        
    def fetch_tagged_data(self, table_id):
        table_with_tags_id = f"{self.dataset_id}.{table_id}_with_tags"
        query = f"SELECT * FROM `{table_with_tags_id}`"
        query_job = self.client.query(query)
        results = query_job.result()
        
        data = []
        for row in results:
            data.append({"contents": row["contents"], "tags": row["tags"]})
        
        return data