//
//  SettingsViewController.swift
//  iCatchUp
//
//  Created by Developer User on 10/18/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import UIKit

class SettingsViewController: UIViewController {

    @IBOutlet weak var sampleImageView: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        let sample = "https://images.pexels.com/photos/58625/pexels-photo-58625.jpeg?cs=srgb&dl=apple-buildings-camera-58625.jpg&fm=jpg"
        sampleImageView.setImageFrom(
            urlString: sample,
            withDefaultNamed: "no-image",
            withErrorNamed: "no-image")
    }


}
