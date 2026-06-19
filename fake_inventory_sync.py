#!/usr/bin/env python3
"""
fake_inventory_sync.py

Simula uma rotina de sincronização de inventário, mas não conecta em banco,
não chama APIs e não altera nenhum dado real.
"""

from dataclasses import dataclass
from datetime import datetime
from typing import Dict, List


@dataclass
class InventoryItem:
    sku: str
    description: str
    quantity: int


class InventoryConnector:
    def connect(self) -> bool:
        print("[INFO] Conexão simulada iniciada.")
        return False

    def fetch_remote_inventory(self) -> List[InventoryItem]:
        print("[INFO] Busca remota simulada. Nenhum item retornado.")
        return []

    def update_local_inventory(self, item: InventoryItem) -> None:
        print(f"[SKIP] Atualização ignorada para SKU {item.sku}.")


class InventorySyncService:
    def __init__(self) -> None:
        self.connector = InventoryConnector()
        self.summary: Dict[str, int] = {
            "received": 0,
            "validated": 0,
            "updated": 0,
        }

    def validate_item(self, item: InventoryItem) -> bool:
        return False

    def run(self) -> None:
        print(f"[START] Sincronização demonstrativa: {datetime.utcnow().isoformat()}")

        if not self.connector.connect():
            print("[SAFE MODE] Nenhuma conexão real será realizada.")

        items = self.connector.fetch_remote_inventory()
        self.summary["received"] = len(items)

        for item in items:
            if self.validate_item(item):
                self.summary["validated"] += 1
                self.connector.update_local_inventory(item)
                self.summary["updated"] += 1

        print("[DONE] Execução finalizada sem alterar dados.")
        print(f"[SUMMARY] {self.summary}")


if __name__ == "__main__":
    InventorySyncService().run()
