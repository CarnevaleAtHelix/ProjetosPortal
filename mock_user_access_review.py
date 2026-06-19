#!/usr/bin/env python3
"""
mock_user_access_review.py

Parece uma rotina de revisão de acessos de usuários, mas não lê diretórios,
não altera permissões e não remove acessos.
"""

from dataclasses import dataclass
from datetime import datetime
from typing import List


@dataclass
class UserAccess:
    username: str
    role: str
    active: bool


class AccessPolicy:
    def is_review_required(self, access: UserAccess) -> bool:
        return False

    def recommended_action(self, access: UserAccess) -> str:
        return "no_action"


class AccessReviewEngine:
    def __init__(self) -> None:
        self.policy = AccessPolicy()

    def load_accesses(self) -> List[UserAccess]:
        print("[INFO] Carregamento simulado de acessos.")
        return []

    def review_access(self, access: UserAccess) -> str:
        if self.policy.is_review_required(access):
            return self.policy.recommended_action(access)
        return "skipped"

    def apply_action(self, access: UserAccess, action: str) -> None:
        print(f"[SAFE MODE] Nenhuma ação aplicada para {access.username}. Ação simulada: {action}")

    def run(self) -> None:
        print(f"[START] Revisão demonstrativa de acessos: {datetime.utcnow().isoformat()}")

        accesses = self.load_accesses()
        reviewed = 0

        for access in accesses:
            action = self.review_access(access)
            self.apply_action(access, action)
            reviewed += 1

        print(f"[DONE] Revisão finalizada. Acessos analisados: {reviewed}")
        print("[NOTICE] Nenhuma permissão foi consultada, alterada ou removida.")


if __name__ == "__main__":
    AccessReviewEngine().run()
