//
//  FavoritesViewController.swift
//  iCatchUp
//
//  Created by Developer User on 11/8/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import Foundation

class FavoritesViewController: ArticlesViewController {
    
    override func viewDidLoad() {
        //self.isRestrictedToFavorites = true
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.isRestrictedToFavorites = true
        super.viewWillAppear(animated)
    }
    
    
}
