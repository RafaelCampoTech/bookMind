from dotenv import load_dotenv
import os

load_dotenv() 


qdrant_cluster_endpoint = os.getenv('QUADRANT_CLUSTER_ENDPOINT')
qdrant_api_key = os.getenv('QUADRANT_API_KEY')

print(f"Loaded Cluster Endpoint: {qdrant_cluster_endpoint}") 
print(f"Loaded API Key: {qdrant_api_key}")