from qdrant_client import QdrantClient
from ..config import qdrant_cluster_endpoint, qdrant_api_key

client = QdrantClient(host=qdrant_cluster_endpoint, api_key=qdrant_api_key)
