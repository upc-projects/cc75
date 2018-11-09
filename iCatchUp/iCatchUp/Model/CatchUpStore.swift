//
//  CatchUpStore.swift
//  iCatchUp
//
//  Created by Developer User on 11/8/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import Foundation

class CatchUpStore {
    
    static let shared = CatchUpStore()
    private init() {}
    
    let favoritesEntity = FavoritesEntity()
    
    func setFavorite(_ isFavorite: Bool, for source: Source) {
        if isFavorite == self.isFavorite(source: source) {
            return
        }
        if isFavorite {
            favoritesEntity.add(from: source)
        } else {
            favoritesEntity.delete(for: source)
        }
    }
    
    func isFavorite(source: Source) -> Bool {
        return favoritesEntity.find(for: source) != nil
    }
    
    func sourceIdsAsString() -> String? {
        return favoritesEntity.sourceIdsAsString()
    }
}
