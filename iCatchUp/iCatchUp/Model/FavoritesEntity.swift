//
//  FavoritesEntity.swift
//  iCatchUp
//
//  Created by Developer User on 11/7/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import Foundation
import UIKit
import CoreData

class FavoritesEntity {
    
    let entityName = "Favorite"
    
    let delegate = UIApplication.shared.delegate as! AppDelegate
    
    var context: NSManagedObjectContext = {
        return (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    }()
    
    func save() {
        delegate.saveContext()
    }
    
    func add(from source: Source) {
        guard find(for: source) == nil else {
            return
        }
        let description = NSEntityDescription.entity(forEntityName: entityName, in: context)
        let entity = NSManagedObject(entity: description!, insertInto: context)
        entity.setValue(source.id, forKey: "sourceId")
        entity.setValue(source.name, forKey: "sourceName")
        save()
    }
    
    func delete(for source: Source) {
        if let favorite = find(for: source) {
            context.delete(favorite)
            save()
        }
    }
    
    func all() -> [NSManagedObject]? {
        return find(withPredicate: nil)
    }
    
    func find(for source: Source) -> NSManagedObject? {
        let predicate = NSPredicate(format: "sourceId = %@", source.id!)
        if let result = find(withPredicate: predicate) {
            return result.first
        }
        return nil
    }
    
    func find(withPredicate predicate: NSPredicate?) -> [NSManagedObject]? {
        let request = NSFetchRequest<NSFetchRequestResult>(entityName: entityName)
        if let predicate = predicate {
            request.predicate = predicate
        }
        do {
            let result = try context.fetch(request)
            return result as? [NSManagedObject]
        } catch {
            print("Error while fetching: \(error.localizedDescription)")
        }
        return nil
        
    }
    
    func sourceIdsAsString() -> String? {
        if let favorites = all() {
            return favorites.map({$0.value(forKey: "sourceId") as! String}).joined()
        }
        return nil
    }
}


